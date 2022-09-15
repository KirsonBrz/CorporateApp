package com.kirson.corporateapp.core.routing.coordinator

import com.kirson.corporateapp.core.routing.Route

interface FlowCoordinator<Event, Result> : FlowCoordinator1<Event, Unit, Result> {
  /**
   * Запускает новый сценарий. Функция [onFlowFinish] будет вызвана по его окончанию с результатом выполнения сценария,
   * переданным в качестве аргумента.
   *
   * Если передан [beforePushClearUntil], все экраны, находящиеся "поверх" этого экрана в стеке подлежат удалению.
   * Это послужит подсказкой координатору (но он волен её игнорировать).
   * После этого будет произведён push initial-route.
   *
   * Если же понадобятся какие-то более изощрённые операции с бэкстеком, то для них уже лучше
   * использовать [FlowCoordinator1] в который передать какие-то специфичные `params` на вход.
   */
  fun start(beforePushClearUntil: Route? = null, onFlowFinish: ((Result) -> Unit)? = null)
}

interface FlowCoordinator1<Event, Params, Result> {
  /**
   * Запускает новый сценарий, принимающий на вход некие параметры [params].
   * Функция [onFlowFinish] будет вызвана по его окончанию с результатом выполнения сценария,
   * переданным в качестве аргумента.
   *
   * Если передан [beforePushClearUntil], все экраны, находящиеся "поверх" этого экрана в стеке подлежат удалению.
   * Это послужит подсказкой координатору (но он волен её игнорировать).
   * После этого будет произведён push initial-route.
   *
   * Если же понадобятся какие-то более изощрённые операции с бэкстеком, то для них уже лучше передать какие-то
   * специфичные `params` на вход.
   */
  fun start(
    params: Params,
    beforePushClearUntil: Route? = null,
    onFlowFinish: ((Result) -> Unit)? = null,
  )

  fun finish(result: Result)

  fun handleEvent(event: Event)
}
