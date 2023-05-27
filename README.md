<p align="center">
  <img width="1200" height="260" src="img/gb_online_chat.png">
</p>

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)

<h2> Назначение проекта </h2>

<h3> OnLine Chat - приложение для обмена текстовыми сообщениями между пользователями (OnLine Chat состоит из клиентской и серверной части)</h3>
<body>
   <dev>
   <p>Сервис позволяет:</p>
     <ul>
     <li>Производить регистрацию пользователей;</li>
     <li>Авторизация пользователей по логину и паролю.;</li>
     <li>Сохранять всю историю переписки между пользователями в отдельном файле;</li>
     <li>Отслеживать состояние пользователей в чате (Online\Offline);</li>
     </ul>
   </dev>
  <dev>
   <p>Сервис в своей работе использует:</p>
   <ul>
     <li>Многопоточную обработку клиентских подключений;</li>
     <li>Для сохранения данных пользователей (логин\пароль) sqlite database;</li>
     <li>На стороне серверной части: библиотека java.net (ServerSocket);</li>
     <li>На стороне клиентской части: платформу сервисных приложений JavaFx;</li>  
     <li>Для сборки проекта используем Apache Maven;</li>
     </ul>
  </dev>
    <dev>
   <p> Окно входа </p>
   <img width="400" height="260" src="img/to come in.png"> 
   <p> Основной экран приложения с тремя пользователями </p>
   <img width="400" height="260" src="img/windows_chat.png"> 
    </dev>   
  
  <hr>

  <dev>
     <h2>Сборка приложения:</h2>

   <dev>
   <p>Требуется выполнить следующие шаги:</p>
     <ul>
     <li>Сделать git clone этого проекта: <a href="https://github.com/AndreyIgnatev/AndreyIgnatev-Chat-Online-Project.git">https://github.com/AndreyIgnatev/AndreyIgnatev-Chat-Online-Project.git</a></li>
     <li>Собрать архив при помощи maven, используя команду в терминале: <code>mvn clean package</code></li>
    </ul>
         </dev>
  </dev>
   <hr>
     <dev>
     <h2>Технические требования:</h2>
<ul> 
<li> Java версии не ниже 8; </li> 
</ul>
</dev>