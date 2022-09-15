package com.kirson.corporateapp.profile.data

import com.kirson.corporateapp.core.data.storage.persistence.TokensPersistence
import com.kirson.corporateapp.profile.data.network.ProfileApi
import com.kirson.corporateapp.profile.domain.ProfileRepository
import javax.inject.Inject

internal class ProfileRepositoryImpl @Inject constructor(
  private val api: ProfileApi,
  private val authPersistence: TokensPersistence,
) : ProfileRepository {

}
