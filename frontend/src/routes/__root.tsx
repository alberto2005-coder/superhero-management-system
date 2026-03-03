import { createRootRoute, Link, Outlet } from '@tanstack/react-router'

export const Route = createRootRoute({
  component: () => (
    <>
      <div className="p-2 flex gap-2 text-lg font-bold border-b mb-4">
        <nav>
          <Link to="/" className="[&.active]:text-blue-500">
            Heroes
          </Link>
          <Link to="/teams" className="[&.active]:text-blue-500">
            Equipos
          </Link>
          <Link to="/create-hero" className="[&.active]:text-blue-500">
            Nuevo Héroe
          </Link>
          <Link to="/create-team" className="[&.active]:text-blue-500">
            Nuevo Equipo
          </Link>
        </nav>
      </div>
      <div className="p-2">
        <Outlet />
      </div>
    </>
  ),
})
