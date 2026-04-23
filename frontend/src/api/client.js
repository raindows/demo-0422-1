const BASE = '/api'

async function request(url, options = {}) {
  const res = await fetch(`${BASE}${url}`, {
    headers: { 'Content-Type': 'application/json' },
    ...options,
    body: options.body ? JSON.stringify(options.body) : undefined,
  })
  if (!res.ok) {
    const err = await res.json().catch(() => ({ error: res.statusText }))
    throw new Error(err.error || res.statusText)
  }
  if (res.status === 204) return null
  return res.json()
}

export const columnApi = {
  getAll: () => request('/columns'),
  create: (data) => request('/columns', { method: 'POST', body: data }),
  update: (id, data) => request(`/columns/${id}`, { method: 'PUT', body: data }),
  remove: (id) => request(`/columns/${id}`, { method: 'DELETE' }),
}

export const taskApi = {
  getAll: (status) => request(status ? `/tasks?status=${status}` : '/tasks'),
  create: (data) => request('/tasks', { method: 'POST', body: data }),
  update: (id, data) => request(`/tasks/${id}`, { method: 'PUT', body: data }),
  remove: (id) => request(`/tasks/${id}`, { method: 'DELETE' }),
}
