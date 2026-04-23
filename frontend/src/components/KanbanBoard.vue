<template>
  <div class="kanban-board">
    <div class="toolbar">
      <button class="btn btn-primary" @click="showColumnForm = true">+ 添加列</button>
    </div>
    <div class="board">
      <KanbanColumn
        v-for="column in columns"
        :key="column.id"
        :column="column"
        @refresh="loadColumns"
      />
    </div>
    <ColumnForm
      v-if="showColumnForm"
      @close="showColumnForm = false"
      @submit="handleCreateColumn"
    />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useColumns } from '../composables/useColumns'
import KanbanColumn from './KanbanColumn.vue'
import ColumnForm from './ColumnForm.vue'

const { columns, loadColumns, createColumn } = useColumns()
const showColumnForm = ref(false)

async function handleCreateColumn(data) {
  await createColumn(data)
  showColumnForm.value = false
}
</script>
