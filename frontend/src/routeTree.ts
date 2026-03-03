import { Route as RootRoute } from './routes/__root'
import { Route as IndexRoute } from './routes/index'
import { Route as TeamsRoute } from './routes/teams'
import { Route as CreateHeroRoute } from './routes/create-hero'
import { Route as CreateTeamRoute } from './routes/create-team'

export const routeTree = RootRoute.addChildren([
  IndexRoute,
  TeamsRoute,
  CreateHeroRoute,
  CreateTeamRoute,
])
