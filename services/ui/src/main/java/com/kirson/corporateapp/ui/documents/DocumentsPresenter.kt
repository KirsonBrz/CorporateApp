package com.kirson.corporateapp.ui.documents

import com.kirson.corporateapp.services.domain.ServicesModel
import com.kirson.corporateapp.core.coroutine.BasePresenter
import com.kirson.corporateapp.routing.AppFlow
import com.kirson.corporateapp.ui.documents.DocumentsScreen.ViewIntents
import com.kirson.corporateapp.ui.documents.DocumentsScreen.ViewState
import com.kirson.corporateapp.ui.documents.entity.Document
import com.kirson.corporateapp.ui.documents.entity.DocumentItemId
import ru.dimsuz.unicorn.coroutines.MachineDsl
import javax.inject.Inject

internal class DocumentsPresenter @Inject constructor(
  private val servicesModel: ServicesModel,
  private val coordinator: AppFlow.Coordinator,
) : BasePresenter<ViewState, ViewIntents, Unit>() {

  override fun MachineDsl<ViewState, Unit>.buildMachine() {
    initial = createInitialState() to null

    onEach(intent(ViewIntents::navigateOnBack)) {
      action { _, _, _ ->
        coordinator.handleEvent(AppFlow.Event.ServiceInfoDismissed)
      }
    }

    onEach(intent(ViewIntents::switchSection)) {
      action { _, _, section ->
        coordinator.handleEvent(AppFlow.Event.Switch(section))
      }
    }

    onEach(intent(ViewIntents::dismissError)) {
      transitionTo { state, _ ->
        state.copy(errorMessage = null)
      }
    }
  }

  private fun createInitialState(): ViewState {
    return ViewState(
        documents = listOf(
            Document(
                id = DocumentItemId(
                    value = "1"
                ),
                title = "Справка о заработной плате",
                description = "1 июн 2022"
            ),
            Document(
                id = DocumentItemId(
                    value = "2"
                ),
                title = "Справка формы 2-НДФЛ",
                description = "17 мая 2022"
            ),
            Document(
                id = DocumentItemId(
                    value = "3"
                ),
                title = "Отчет 1-й квартал 2022",
                description = "1 окт 2022"
            ),
            Document(
                id = DocumentItemId(
                    value = "4"
                ),
                title = "Отчет 3-й квартал 2022",
                description = "1 апр 2022"
            ),
            Document(
                id = DocumentItemId(
                    value = "5"
                ),
                title = "Отчет 4-й квартал 2022",
                description = "1 июн 2022"
            ),
            Document(
                id = DocumentItemId(
                    value = "6"
                ),
                title = "Шаблон паспорт",
                description = "1 янв 2022"
            ),

        )
    )
  }
}
