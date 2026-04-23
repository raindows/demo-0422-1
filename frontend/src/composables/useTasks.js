import { ref } from 'vue'
import { taskApi } from '../api/client'

export function useTasks() {
  const tasks = ref([])

  async function loadTasks(status) {
    tasks.value = await taskApi.getAll(status)
  }

  async function createTask(data) {
    await taskApi.create(data)
  }

  async function updateTask(id, data) {
    await taskApi.update(id, data)
  }

  async function deleteTask(id) {
    await taskApi.remove(id)
  }

  return { tasks, loadTasks, createTask, updateTask, deleteTask }
}
