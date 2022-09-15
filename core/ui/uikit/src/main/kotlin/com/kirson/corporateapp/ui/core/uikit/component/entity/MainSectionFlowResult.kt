package com.kirson.corporateapp.ui.core.uikit.component.entity

enum class MainSectionFlowResult {
  /**
   * User switched to another tab, replacing the current flow with another one
   */
  Replaced,

  /**
   * User has pressed Back button on the root screen of the current flow
   */
  Dismissed,
}
