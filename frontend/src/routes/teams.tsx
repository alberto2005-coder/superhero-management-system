import { createRoute } from '@tanstack/react-router'
import { Route as RootRoute } from './__root'
import TeamList from '../components/TeamList'

export const Route = createRoute({
  getParentRoute: () => RootRoute,
  path: '/teams',
  component: TeamList,
})
