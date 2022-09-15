package com.kirson.corporateapp.profile.domain.di

import com.kirson.corporateapp.profile.domain.ProfileModel
import com.kirson.corporateapp.profile.domain.ProfileModelImpl
import toothpick.config.Module

class ProfileDomainModule : Module() {
  init {
    bind(ProfileModel::class.java)
      .to(ProfileModelImpl::class.java)
      .singletonInScope()
  }
}