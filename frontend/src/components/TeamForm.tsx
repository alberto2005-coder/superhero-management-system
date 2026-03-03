import { useState } from 'react'
import { useMutation, useQueryClient } from '@tanstack/react-query'
import { createEquipo } from '../api/teams'
import { useNavigate } from '@tanstack/react-router'

export default function TeamForm() {
  const navigate = useNavigate()
  const queryClient = useQueryClient()
  const [nombre, setNombre] = useState('')

  const createMutation = useMutation({
    mutationFn: createEquipo,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['equipos'] })
      navigate({ to: '/teams' })
    },
  })

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    createMutation.mutate({ nombre })
  }

  return (
    <div className="container">
      <h1 className="page-title">Crear Nuevo Equipo</h1>
      <form onSubmit={handleSubmit} className="create-form">
        <div className="form-group">
          <label>Nombre del Equipo</label>
          <input 
            type="text" 
            value={nombre}
            onChange={e => setNombre(e.target.value)}
            required
            className="form-input"
            placeholder="Ej: Vengadores"
          />
        </div>
        
        <button type="submit" className="submit-btn" disabled={createMutation.isPending}>
          {createMutation.isPending ? 'Creando...' : 'Crear Equipo'}
        </button>
      </form>
    </div>
  )
}
