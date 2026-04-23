import { ref, onMounted } from 'vue'
import { columnApi } from '../api/client'

export function useColumns() {
  const columns = ref([])

  async function loadColumns() {
    columns.value = await columnApi.getAll()
  }

  async function createColumn(data) {
    await columnApi.create(data)
    await loadColumns()
  }

  async function updateColumn(id, data) {
    await columnApi.update(id, data)
    await loadColumns()
  }

  async function deleteColumn(id) {
    await columnApi.remove(id)
    await loadColumns()
  }

  onMounted(loadColumns)

  return { columns, loadColumns, createColumn, updateColumn, deleteColumn }
}
