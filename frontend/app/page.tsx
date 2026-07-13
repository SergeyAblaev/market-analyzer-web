import {
  Activity,
  Bell,
  ChartCandlestick,
  Database,
  ExternalLink,
  RadioTower,
  Workflow,
} from "lucide-react";

import { Button } from "@/components/ui/button";

const pipeline = [
  {
    label: "Binance WebSocket",
    detail: "Trade stream ingress",
    icon: RadioTower,
    tone: "text-sky-600 bg-sky-50 border-sky-200",
  },
  {
    label: "OHLC Aggregator",
    detail: "Closed candle builder",
    icon: ChartCandlestick,
    tone: "text-emerald-600 bg-emerald-50 border-emerald-200",
  },
  {
    label: "RuleEngine",
    detail: "Stateful candle rules",
    icon: Workflow,
    tone: "text-amber-600 bg-amber-50 border-amber-200",
  },
  {
    label: "macOS Alert",
    detail: "Local alert output",
    icon: Bell,
    tone: "text-rose-600 bg-rose-50 border-rose-200",
  },
];

const apiRoutes = [
  ["/api/v1/market/candles", "Candle and historical market data"],
  ["/api/v1/tickers", "Ticker management and information"],
  ["/api/v1/alerts", "Generated alert access"],
  ["/api/v1/alertrules", "Alert rule management"],
];

const ruleTypes = ["PriceThresholdRule", "PercentChangeRule", "ImpulseMoveRule"];

export default function Home() {
  return (
    <main className="min-h-screen bg-background text-foreground">
      <section className="border-b bg-muted/30">
        <div className="mx-auto flex w-full max-w-7xl flex-col gap-8 px-6 py-8 lg:px-10">
          <div className="flex flex-col gap-5 lg:flex-row lg:items-end lg:justify-between">
            <div className="max-w-3xl space-y-4">
              <div className="inline-flex items-center gap-2 rounded-md border bg-background px-3 py-1 text-sm font-medium text-muted-foreground">
                <Activity className="size-4 text-emerald-600" aria-hidden />
                Next.js frontend baseline
              </div>
              <div className="space-y-3">
                <h1 className="text-4xl font-semibold tracking-normal text-balance md:text-5xl">
                  Market Analyzer
                </h1>
                <p className="max-w-2xl text-base leading-7 text-muted-foreground md:text-lg">
                  Standalone App Router shell for the current Spring Boot market
                  data runtime: Binance trades, OHLC candles, stateful rules,
                  alerts, persistence, REST API, Actuator, Prometheus and
                  OpenAPI.
                </p>
              </div>
            </div>

            <div className="flex flex-wrap gap-2">
              <Button
                render={
                  <a
                    href="http://localhost:8080/swagger-ui/index.html"
                    target="_blank"
                    rel="noreferrer"
                  />
                }
                nativeButton={false}
              >
                <ExternalLink aria-hidden />
                Swagger UI
              </Button>
              <Button
                variant="outline"
                render={
                  <a
                    href="http://localhost:8080/actuator"
                    target="_blank"
                    rel="noreferrer"
                  />
                }
                nativeButton={false}
              >
                <ExternalLink aria-hidden />
                Actuator
              </Button>
            </div>
          </div>

          <div className="grid gap-3 md:grid-cols-4">
            {pipeline.map((item) => {
              const Icon = item.icon;

              return (
                <article
                  key={item.label}
                  className="rounded-lg border bg-background p-4 shadow-xs"
                >
                  <div
                    className={`mb-4 inline-flex size-10 items-center justify-center rounded-md border ${item.tone}`}
                  >
                    <Icon className="size-5" aria-hidden />
                  </div>
                  <h2 className="text-sm font-semibold">{item.label}</h2>
                  <p className="mt-1 text-sm text-muted-foreground">
                    {item.detail}
                  </p>
                </article>
              );
            })}
          </div>
        </div>
      </section>

      <section className="mx-auto grid w-full max-w-7xl gap-6 px-6 py-8 lg:grid-cols-[1.4fr_0.9fr] lg:px-10">
        <div className="rounded-lg border bg-card p-5 shadow-xs">
          <div className="mb-5 flex items-center justify-between gap-4">
            <div>
              <h2 className="text-xl font-semibold">Confirmed HTTP API</h2>
              <p className="mt-1 text-sm text-muted-foreground">
                Backend remains the source of business rules and data contracts.
              </p>
            </div>
            <Database className="size-5 text-muted-foreground" aria-hidden />
          </div>

          <div className="divide-y">
            {apiRoutes.map(([route, description]) => (
              <div
                key={route}
                className="grid gap-2 py-3 sm:grid-cols-[minmax(0,0.75fr)_1fr]"
              >
                <code className="text-sm font-medium text-foreground">
                  {route}
                </code>
                <span className="text-sm text-muted-foreground">
                  {description}
                </span>
              </div>
            ))}
          </div>
        </div>

        <aside className="rounded-lg border bg-card p-5 shadow-xs">
          <div className="mb-5 flex items-center justify-between gap-4">
            <div>
              <h2 className="text-xl font-semibold">Runtime Notes</h2>
              <p className="mt-1 text-sm text-muted-foreground">
                Factual baseline from docs/current-architecture.md.
              </p>
            </div>
            <Workflow className="size-5 text-muted-foreground" aria-hidden />
          </div>

          <dl className="space-y-4">
            <div>
              <dt className="text-sm font-medium">Persistence</dt>
              <dd className="mt-1 text-sm text-muted-foreground">
                Spring Data JPA with H2 in the confirmed current baseline.
              </dd>
            </div>
            <div>
              <dt className="text-sm font-medium">Rule types</dt>
              <dd className="mt-2 flex flex-wrap gap-2">
                {ruleTypes.map((rule) => (
                  <span
                    key={rule}
                    className="rounded-md border bg-muted px-2 py-1 text-xs font-medium text-muted-foreground"
                  >
                    {rule}
                  </span>
                ))}
              </dd>
            </div>
            <div>
              <dt className="text-sm font-medium">Not in this step</dt>
              <dd className="mt-1 text-sm text-muted-foreground">
                Kafka, authentication, generated API client and ECharts are not
                represented as implemented runtime components yet.
              </dd>
            </div>
          </dl>
        </aside>
      </section>
    </main>
  );
}
