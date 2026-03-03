import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { getHeroes, deleteHeroe } from '../api/heroes'
import { Trash2 } from 'lucide-react'

export default function HeroList() {
  const queryClient = useQueryClient()
  
  const { data: heroes, isLoading, error } = useQuery({
    queryKey: ['heroes'],
    queryFn: getHeroes,
  })

  const deleteMutation = useMutation({
    mutationFn: deleteHeroe,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['heroes'] })
    },
  })

  if (isLoading) return <div className="loading">Cargando héroes...</div>
  if (error) return <div className="error">Error al cargar héroes</div>

  return (
    <div className="container">
      <h1 className="page-title">Héroes Registrados</h1>
      <div className="hero-grid">
        {heroes?.map((hero) => (
          <div key={hero.id} className="hero-card">
            <div className="hero-avatar">
               <img 
                src={`https://ui-avatars.com/api/?name=${hero.nombre}&background=random`} 
                alt={hero.nombre} 
              />
            </div>
            <div className="hero-info">
              <h3>{hero.nombre}</h3>
              <p className="hero-universe">{hero.universo}</p>
              <p className="hero-base">Base: {hero.base}</p>
              {hero.equipo && <p className="hero-team">Equipo: {hero.equipo.nombre}</p>}
            </div>
            <button 
              className="delete-btn"
              onClick={() => {
                if(window.confirm('¿Eliminar héroe?')) {
                  deleteMutation.mutate(hero.id)
                }
              }}
            >
              <Trash2 size={18} />
            </button>
          </div>
        ))}
      </div>
    </div>
  )
}
