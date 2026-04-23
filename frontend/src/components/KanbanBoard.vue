<template>
  <div class="kanban-board">
    <div class="toolbar">
      <button class="btn btn-primary" @click="showColumnForm = true">
        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="12" y1="5" x2="12" y2="19"/><line x1="5" y1="12" x2="19" y2="12"/></svg>
        添加列
      </button>
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
