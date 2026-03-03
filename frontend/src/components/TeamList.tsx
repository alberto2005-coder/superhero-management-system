import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { getEquipos, deleteEquipo } from '../api/teams'
import { Trash2, Users } from 'lucide-react'

export default function TeamList() {
  const queryClient = useQueryClient()

  const { data: equipos, isLoading, error } = useQuery({
    queryKey: ['equipos'],
    queryFn: getEquipos,
  })

  const deleteMutation = useMutation({
    mutationFn: deleteEquipo,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['equipos'] })
    },
  })

  if (isLoading) return <div className="loading">Cargando equipos...</div>
  if (error) return <div className="error">Error al cargar equipos</div>

  return (
    <div className="container">
      <h1 className="page-title">Equipos de Superhéroes</h1>
      <div className="team-grid">
        {equipos?.map((equipo) => (
          <div key={equipo.id} className="team-card">
            <div className="team-header">
              <Users size={24} className="team-icon" />
              <h3>{equipo.nombre}</h3>
              <button 
                className="delete-btn-small"
                onClick={() => {
                  if(window.confirm('¿Eliminar equipo?')) {
                    deleteMutation.mutate(equipo.id)
                  }
                }}
              >
                <Trash2 size={16} />
              </button>
            </div>
            <div className="team-members">
              <h4>Miembros ({equipo.heroes?.length || 0})</h4>
              <div className="member-thumbnails">
                {equipo.heroes?.map((hero) => (
                  <div key={hero.id} className="member-thumbnail" title={hero.nombre}>
                    <img 
                      src={`https://ui-avatars.com/api/?name=${hero.nombre}&background=random&size=32`} 
                      alt={hero.nombre} 
                    />
                  </div>
                ))}
                {(!equipo.heroes || equipo.heroes.length === 0) && (
                  <span className="no-members">Sin miembros</span>
                )}
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}
