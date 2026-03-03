import { useState } from 'react'
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query'
import { createHeroe } from '../api/heroes'
import { getEquipos } from '../api/teams'
import { useNavigate } from '@tanstack/react-router'

export default function HeroForm() {
  const navigate = useNavigate()
  const queryClient = useQueryClient()
  
  const [formData, setFormData] = useState({
    nombre: '',
    universo: '',
    base: '',
    equipoId: ''
  })

  const { data: equipos } = useQuery({
    queryKey: ['equipos'],
    queryFn: getEquipos
  })

  const createMutation = useMutation({
    mutationFn: createHeroe,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['heroes'] })
      navigate({ to: '/' })
    },
  })

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    const heroeData = {
      nombre: formData.nombre,
      universo: formData.universo,
      base: formData.base,
      
    }
    
       createMutation.mutate(heroeData)
  }

  return (
    <div className="container">
      <h1 className="page-title">Registrar Nuevo Héroe</h1>
      <form onSubmit={handleSubmit} className="create-form">
        <div className="form-group">
          <label>Nombre</label>
          <input 
            type="text" 
            value={formData.nombre}
            onChange={e => setFormData({...formData, nombre: e.target.value})}
            required
            className="form-input"
          />
        </div>
        
        <div className="form-group">
          <label>Universo</label>
          <select 
            value={formData.universo}
            onChange={e => setFormData({...formData, universo: e.target.value})}
            className="form-input"
          >
            <option value="">Seleccionar Universo...</option>
            <option value="MARVEL">Marvel</option>
            <option value="DC">DC</option>
            <option value="OTHER">Otro</option>
          </select>
        </div>

        {/* Team selection */}
        <div className="form-group">
          <label>Equipo (Opcional)</label>
          <select 
            value={formData.equipoId}
            onChange={e => setFormData({...formData, equipoId: e.target.value})}
            className="form-input"
          >
            <option value="">Sin Equipo</option>
            {equipos?.map(equipo => (
              <option key={equipo.id} value={equipo.id}>
                {equipo.nombre}
              </option>
            ))}
          </select>
          <input 
            type="text" 
            value={formData.base}
            onChange={e => setFormData({...formData, base: e.target.value})}
            className="form-input"
          />
        </div>

        {/* Team selection could go here if backend supported it directly on create */}
        
        <button type="submit" className="submit-btn" disabled={createMutation.isPending}>
          {createMutation.isPending ? 'Guardando...' : 'Guardar Héroe'}
        </button>
      </form>
    </div>
  )
}
