<template>
  <div class="kanban-column">
    <div class="column-header">
      <h3>{{ column.name }}</h3>
      <button class="btn-icon" title="删除列" @click="handleDelete">
        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polyline points="3 6 5 6 21 6"/><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"/></svg>
      </button>
    </div>
    <div class="column-body">
      <KanbanTask
        v-for="task in tasks"
        :key="task.id"
        :task="task"
        :columns="columns"
        @refresh="loadTasks"
      />
      <div v-if="tasks.length === 0" class="column-empty">暂无任务</div>
    </div>
    <div class="column-footer">
      <button class="btn btn-add-task" @click="showTaskForm = true">
        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="12" y1="5" x2="12" y2="19"/><line x1="5" y1="12" x2="19" y2="12"/></svg>
        添加任务
      </button>
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
