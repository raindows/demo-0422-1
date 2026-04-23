<template>
  <div class="kanban-column">
    <div class="column-header">
      <h3>{{ column.name }}</h3>
      <div class="column-actions">
        <button class="btn-icon" title="删除列" @click="handleDelete">&times;</button>
      </div>
    </div>
    <div class="column-body">
      <KanbanTask
        v-for="task in tasks"
        :key="task.id"
        :task="task"
        :columns="columns"
        @refresh="loadTasks"
      />
    </div>
    <div class="column-footer">
      <button class="btn btn-sm" @click="showTaskForm = true">+ 添加任务</button>
    </div>
    <TaskForm
      v-if="showTaskForm"
      :column-id="column.id"
      :columns="columns"
      @close="showTaskForm = false"
      @submit="handleCreateTask"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useTasks } from '../composables/useTasks'
import { useColumns } from '../composables/useColumns'
import KanbanTask from './KanbanTask.vue'
import TaskForm from './TaskForm.vue'

const props = defineProps({
  column: { type: Object, required: true },
})

const emit = defineEmits(['refresh'])

const { columns } = useColumns()
const { tasks, loadTasks, createTask } = useTasks()
const showTaskForm = ref(false)

onMounted(() => loadTasks(props.column.id))

async function handleCreateTask(data) {
  await createTask(data)
  showTaskForm.value = false
  await loadTasks(props.column.id)
}

async function handleDelete() {
  if (confirm(`确定删除列 "${props.column.name}" 吗？该列下的任务状态将被清空。`)) {
    const { columnApi } = await import('../api/client')
    await columnApi.remove(props.column.id)
    emit('refresh')
  }
}
</script>
