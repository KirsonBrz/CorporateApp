package com.kirson.corporateapp.core.util

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.unwrap
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

inline fun <T1 : Any, T2 : Any, E> Observable<Result<T1, E>>.flatMapOk(
  crossinline mapper: (T1) -> Observable<T2>,
): Observable<Result<T2, E>> {
  return this.flatMap { result ->
    when (result) {
      is Ok -> mapper(result.value).map { v: T2 -> Ok(v) }
      is Err -> Observable.just(result)
    }
  }
}

inline fun <T1 : Any, T2 : Any, E> Single<Result<T1, E>>.flatMapOk(
  crossinline mapper: (T1) -> Single<T2>,
): Single<Result<T2, E>> {
  return this.flatMap { result ->
    when (result) {
      is Ok -> mapper(result.value).map { v: T2 -> Ok(v) }
      is Err -> Single.just(result)
    }
  }
}

fun <T : Any, E> Observable<Result<T, E>>.filterOk(): Observable<T> {
  return this.filter { it is Ok }.map { it.unwrap() }
}

fun <T : Any> Observable<T>.mapToResult(): Observable<Result<T, Throwable>> {
  return this.map<Result<T, Throwable>> { Ok(it) }.onErrorReturn { Err(it) }
}

fun <T : Any> Single<T>.mapToResult(): Single<Result<T, Throwable>> {
  return this.map<Result<T, Throwable>> { Ok(it) }.onErrorReturn { Err(it) }
}

fun <T : Any> Completable.mapToResult(defaultValue: T): Single<Result<T, Throwable>> {
  return this.andThen<Result<T, Throwable>>(Single.just(Ok(defaultValue))).onErrorReturn { Err(it) }
}
