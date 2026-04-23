<template>
  <Teleport to="body">
    <div class="modal-overlay" @click.self="$emit('close')">
      <div class="modal">
        <h3>{{ task ? '编辑任务' : '添加任务' }}</h3>
        <form @submit.prevent="handleSubmit">
          <div class="form-group">
            <label>标题</label>
            <input v-model="form.title" type="text" required placeholder="任务标题" />
          </div>
          <div class="form-group">
            <label>描述</label>
            <textarea v-model="form.description" rows="3" placeholder="任务描述（可选）"></textarea>
          </div>
          <div class="form-group">
            <label>所属列</label>
            <select :value="form.status" @change="form.status = $event.target.value ? Number($event.target.value) : null">
              <option value="">未分配</option>
              <option v-for="col in columns" :key="col.id" :value="String(col.id)">{{ col.name }}</option>
            </select>
          </div>
          <div class="form-group">
            <label>优先级</label>
            <select v-model="form.priority">
              <option value="high">High</option>
              <option value="medium">Medium</option>
              <option value="low">Low</option>
            </select>
          </div>
          <div class="form-actions">
            <button type="button" class="btn btn-ghost" @click="$emit('close')">取消</button>
            <button type="submit" class="btn btn-primary">保存</button>
          </div>
        </form>
      </div>
    </div>
  </Teleport>
</template>

<script setup>
import { reactive } from 'vue'

const props = defineProps({
  task: { type: Object, default: null },
  columnId: { type: Number, default: null },
  columns: { type: Array, default: () => [] },
})

const emit = defineEmits(['close', 'submit'])

const form = reactive({
  title: props.task?.title || '',
  description: props.task?.description || '',
  status: props.task?.status ?? props.columnId ?? null,
  priority: props.task?.priority || 'medium',
})

function handleSubmit() {
  emit('submit', { ...form })
}
</script>
