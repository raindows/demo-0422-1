<template>
  <div class="modal-overlay" @click.self="$emit('close')">
    <div class="modal">
      <h3>{{ column ? '编辑列' : '添加列' }}</h3>
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <label>名称</label>
          <input v-model="form.name" type="text" required placeholder="列名称" />
        </div>
        <div class="form-group">
          <label>位置</label>
          <input v-model.number="form.position" type="number" min="0" required />
        </div>
        <div class="form-actions">
          <button type="button" class="btn" @click="$emit('close')">取消</button>
          <button type="submit" class="btn btn-primary">保存</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'

const props = defineProps({
  column: { type: Object, default: null },
})

const emit = defineEmits(['close', 'submit'])

const form = reactive({
  name: props.column?.name || '',
  position: props.column?.position ?? 0,
})

function handleSubmit() {
  emit('submit', { ...form })
}
</script>
