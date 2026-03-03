import { createRoute } from '@tanstack/react-router'
import { Route as RootRoute } from './__root'
import HeroList from '../components/HeroList'

export const Route = createRoute({
  getParentRoute: () => RootRoute,
  path: '/',
  component: HeroList,
})
