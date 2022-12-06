<h1 align="left">Приложение, реализованное в рамках выпускной работы бакалавра ETU 2022</h1>


<h2 align="left">Стэк: </h2>

- MVI Clean Architecture project
- Dagger2
- Firebase Storage, Auth, Crash Analytics
- Kotlin Coroutines

<h2 align="left">Принцип работы: </h2>

![image](https://user-images.githubusercontent.com/52128742/205921311-43487d98-530c-40e0-a365-0e3056d2716d.png)

I

Пользователь проходит аутентификацию

II

Пользователь открывает список документов, может загрузить документ на устройство

III

Пользователь открывает раздел с заявками, может просмотреть отправленные, создать новую, либо ответить специалисту внутри созданной заявки

IV

Пользователь открывает профиль, где может просмотреть и отредактировать свои личные данные




<h2 align="left">Реализованы следующие функциональные модули: </h1>

1) Модуль auth, отвечающий за аутентификацию

2) Модуль services, предназначенный для реализации различных сервисов компании, и реализована работа с документацией

3) Модуль orders, служащий для работы с заявками

4) Модуль profile для работы с личной информацией

- Для иньекции зависимостей используется Dagger2
- Модуль rooting собирает зависимости и служит для навигации
