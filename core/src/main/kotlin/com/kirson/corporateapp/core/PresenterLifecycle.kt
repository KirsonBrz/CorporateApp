package com.kirson.corporateapp.core

// TODO @dz this interface is here only until transition to coroutines is complete, because
//  currently there are 2 presenters (Rx and Coroutine based).
//  After only one of them left, remove this interface and replace all occurrences with "BasePresenter"
//  Related task: https://jira.kode.ru/browse/DI-634
interface PresenterLifecycle<VS : Any, VI : BaseViewIntents> {
  fun attachView(view: MviView<VS, VI>)
  fun detachView()
  fun destroy()
}
