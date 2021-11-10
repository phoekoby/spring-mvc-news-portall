# spring-mvc-news-portall

Как запустить приложение?
На компьютере должен быть jdk или jre 11+.
Чтобы запустить приложение:
1. способ:
    1.скачать target.zip, разаахивировать в любую удобную папку
    2.Зайти в папку target, где лежит файл news-spring-0.0.1-SNAPSHOT.jar и запустить
2. способ:
    1.скачать target.zip, разаахивировать в любую удобную папку
    2.Зайти в папку target, где лежит файл news-spring-0.0.1-SNAPSHOT.jar
    3.Набрать в адресной строке Проводника cmd и нажать клавишу Enter или стрелку вправо справа от строки.
    4.В командной строке набрать команду java -jar news-spring-0.0.1-SNAPSHOT.jar
3. способ (через Idea):
    1.Откройте проект в IDE (file->new->project from version control и вставляете ссылку в поле URL)
    2.Соберите проект, вбив в командную строку mvn clean install
    3.Запустите, используя команду java -jar ./target/news-spring-0.0.1-SNAPSHOT.jar
4. способ (через Idea):
    1.Откройте проект в IDE ((file->new->project from version control и вставляете ссылку в поле URL)
    2.Запустите проект с помощью комбинации клавиш Shift+F10
  
  
Приложение запустилось, нужно зайти в любой удобный браузер и перейти по адресу 
localhost:8080/news


Как работать с сайтом?
1.Вы находитесь на главной странице сайта, можете листать новости по ссылкам внизу страницы.
2.Для того, чтобы отфильтровать сайты по определенной категории, выберите категорию вверху страницы на навигационной панели.
3.Для того, чтобы прочитать новость полностью, нажмите на ссылку "Читать полностью"
4.Для того, чтобы добавить новость, нажмите на "Добавить новость" в навигационной панели, затем: 
    1.выберите архив,
      в котором содержится только один файл с новостью, где первая строка - заголовок, остальное - сама новость, загрузите его.
    2.Выберите категорию, к которой относится эта новость.
    3.Нажмите на кнопку Upload.
    4.Ура ваша новость успешно загружена!