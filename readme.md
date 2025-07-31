🐧 Selenoid Deployment with Ansible
📋 Requirements
Ansible installed on your local machine (e.g., macOS or Linux)
Remote Ubuntu server accessible via SSH
Remote user: julia
▶️ Command to Run
cd playbook
ansible-playbook -i hosts tasks.yaml --ask-become-pass
# 1. Клонируем репозиторий
git clone https://github.com/YuliaZhuko/otus.git

# 2. Переходим в папку репозитория
cd otus

# 3. Переключаемся на ветку homework_3
git checkout homework_3


# 4. Запуск тестов из этой ветки
mvn test -Dbase.url=https://petstore.swagger.io/v2/