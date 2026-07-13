# Market Analyzer Frontend

Standalone Next.js frontend for the current Market Analyzer backend baseline.

## Stack

- Next.js App Router
- TypeScript
- ESLint
- Tailwind CSS
- shadcn/ui

## Development

```bash
npm run dev
```

Open http://localhost:3000.

## Validation

```bash
npm run lint
npm run build
```

The initial page reflects the confirmed architecture from
`docs/current-architecture.md`: Binance WebSocket market ingestion, OHLC candle
aggregation, stateful rule evaluation, generated alerts, JPA/H2 persistence,
REST API, Actuator, Prometheus and OpenAPI.
