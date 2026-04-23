<template>
  <div class="kanban-task" :class="'kanban-task--priority-' + (task.priority || 'medium')">
    <div class="task-content" @click="showForm = true">
      <strong>{{ task.title }}</strong>
      <p v-if="task.description">{{ task.description }}</p>
    </div>
    <button class="btn-icon btn-delete" title="删除任务" @click.stop="handleDelete">
      <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/></svg>
    </button>
    <TaskForm
      v-if="showForm"
      :task="task"
      :columns="columns"
      @close="showForm = false"
      @submit="handleUpdate"
    />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { taskApi } from '../api/client'
import TaskForm from './TaskForm.vue'

const props = defineProps({
  task: { type: Object, required: true },
  columns: { type: Array, default: () => [] },
})

const emit = defineEmits(['refresh'])
const showForm = ref(false)

async function handleUpdate(data) {
  await taskApi.update(props.task.id, data)
  showForm.value = false
  emit('refresh')
}

async function handleDelete() {
  if (confirm(`确定删除任务 "${props.task.title}" 吗？`)) {
    await taskApi.remove(props.task.id)
    emit('refresh')
  }
}
</script>
