package com.kirson.corporateapp.services.data

import com.kirson.corporateapp.services.domain.ServicesRepository
import com.kirson.corporateapp.services.data.network.ServiceApi
import com.kirson.corporateapp.core.data.storage.persistence.TokensPersistence
import javax.inject.Inject

internal class ServicesRepositoryImpl @Inject constructor(
  private val api: ServiceApi,
  private val authPersistence: TokensPersistence,
) : ServicesRepository {

}
