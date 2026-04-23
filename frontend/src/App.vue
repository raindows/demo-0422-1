<template>
  <div id="app">
    <nav class="navbar">
      <div class="nav-inner">
        <div class="nav-logo">
          <div class="nav-logo-icon">
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"><rect x="3" y="3" width="7" height="7" rx="1"/><rect x="14" y="3" width="7" height="7" rx="1"/><rect x="3" y="14" width="7" height="7" rx="1"/><rect x="14" y="14" width="7" height="7" rx="1"/></svg>
          </div>
          KanbanFlow
        </div>
        <button class="btn btn-primary" @click="showColumnForm = true">
          <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"><line x1="12" y1="5" x2="12" y2="19"/><line x1="5" y1="12" x2="19" y2="12"/></svg>
          添加列
        </button>
      </div>
    </nav>

    <main class="board-main">
      <div class="board">
        <KanbanColumn
          v-for="column in columns"
          :key="column.id"
          :column="column"
          :columns="columns"
          @refresh="loadColumns"
        />
      </div>
    </main>

    <ColumnForm
      v-if="showColumnForm"
      @close="showColumnForm = false"
      @submit="handleCreateColumn"
    />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useColumns } from './composables/useColumns'
import KanbanColumn from './components/KanbanColumn.vue'
import ColumnForm from './components/ColumnForm.vue'

const { columns, loadColumns, createColumn } = useColumns()
const showColumnForm = ref(false)

async function handleCreateColumn(data) {
  await createColumn(data)
  showColumnForm.value = false
}
</script>
