import { createRoute } from '@tanstack/react-router'
import { Route as RootRoute } from './__root'
import HeroForm from '../components/HeroForm'

export const Route = createRoute({
  getParentRoute: () => RootRoute,
  path: '/create-hero',
  component: HeroForm,
})
