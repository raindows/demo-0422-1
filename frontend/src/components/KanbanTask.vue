<template>
  <div class="kanban-task">
    <div class="task-content" @click="showForm = true">
      <strong>{{ task.title }}</strong>
      <p v-if="task.description">{{ task.description }}</p>
    </div>
    <button class="btn-icon btn-delete" title="删除任务" @click.stop="handleDelete">&times;</button>
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
