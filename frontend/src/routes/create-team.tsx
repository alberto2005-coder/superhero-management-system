import { createRoute } from '@tanstack/react-router'
import { Route as RootRoute } from './__root'
import TeamForm from '../components/TeamForm'

export const Route = createRoute({
  getParentRoute: () => RootRoute,
  path: '/create-team',
  component: TeamForm,
})
