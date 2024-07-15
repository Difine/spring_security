jQuery(async function () {

    let allRoles = await getAllRoles()

    // Вывод информации об авторизированном пользователе
    async function getUserInfo() {
        let response = await fetch('../api/users/userinfo');
        let userInfoJSON = await response.json();
        let roleList = ''
        userInfoJSON.roles.forEach((role) => {
            roleList += role.name.replaceAll('ROLE_', '') + ' ';
        })
        $('.userinfo').text(userInfoJSON.username.toUpperCase() + ' with roles: ' + roleList);

    }

    // Вывод всех пользователей из БД в таблицу
    async function getAllUsers() {
        const userList = $('.userList');
        let response = await fetch('../api/users')
        let allUsers = await response.json()

        userList.html('')
        allUsers.forEach((el) => {
            let roleList = ''
            el.roles.forEach((role) => {
                roleList += role.name.replaceAll('ROLE_', '') + ' ';
            })

            userList.append(
                '<tr>' +
                '<th scope="row">' + el.id + '</th>' +
                '<td>' + el.name + '</td>' +
                '<td>' + el.lastName + '</td>' +
                '<td>' + el.age + '</td>' +
                '<td>' + el.username + '</td>' +
                '<td>' + roleList + '</td>' +
                `<td> <button type="button" class="btn btn-info text-light btnEditModal" data-id="${el.id}" data-name="${el.name}" data-lastName="${el.lastName}" data-age="${el.age}" data-username="${el.username}" data-bs-toggle="modal" data-bs-target="#editModal"> Edit </button> </td>` +
                `<td> <button type="button" class="btn btn-danger text-light btnDeleteModal" data-id="${el.id}" data-name="${el.name}" data-lastName="${el.lastName}" data-age="${el.age}" data-username="${el.username}" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete</button></td>` +
                '</tr>'
            )
        });
    }

    // Получение списка возможных ролей из БД
    async function getAllRoles() {
        let response = await fetch('../api/roles')
        return await response.json()
    }


    allRoles.forEach((el) => {
        $('#editRolesSelect').append(`<option value="${el.name}">${el.name}</option>>`)
        $('#addRolesSelect').append(`<option value="${el.name}">${el.name}</option>>`)
        $('#deleteRolesSelect').append(`<option value="${el.name}">${el.name}</option>>`)
    })

    // Вывод модального окна для редактирования
    $(document).on('click', '.btnEditModal', function () {
        let userId = $(this).attr('data-id');
        $('#editModal input[name="id"]').val(userId)
        $('#editModal input[name="name"]').val($(this).attr('data-name'))
        $('#editModal input[name="lastName"]').val($(this).attr('data-lastName'))
        $('#editModal input[name="age"]').val($(this).attr('data-age'))
        $('#editModal input[name="username"]').val($(this).attr('data-username'))
        $('#editModal input[name="password"]').val('')

    })

    // Подтверждение редактирования пользователя
    $(document).on('click', '.submitEditUser', async function () {
        const editForm = $('#editForm')[0]
        const formData = new FormData(editForm)

        let user = {
            id: formData.get('id'),
            name: formData.get('name'),
            lastName: formData.get('lastName'),
            age: formData.get('age'),
            username: formData.get('username'),
            password: formData.get('password'),
            roles: $('#editRolesSelect').val().map(role => ({name: role}))
        };

        const response = await fetch('../api/users', {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })

        if (response.ok) {
            await getAllUsers();
            alert('User has been edited')
        } else {
            alert('An error occurred while trying to edit')
        }
    })

    // Вывод модального окна для удаления
    $(document).on('click', '.btnDeleteModal', function () {
        let userId = $(this).attr('data-id');
        $('#deleteModal input[name="id"]').val(userId)
        $('#deleteModal input[name="name"]').val($(this).attr('data-name'))
        $('#deleteModal input[name="lastName"]').val($(this).attr('data-lastName'))
        $('#deleteModal input[name="age"]').val($(this).attr('data-age'))
        $('#deleteModal input[name="username"]').val($(this).attr('data-username'))
        $('#deleteModal input[name="password"]').val('')
    })

    // Подтверждение удаления пользователя
    $(document).on('click', '.submitDeleteUser', async function () {
        let userid = $('#deleteModal input[name="id"]').val()

        const response = await fetch('../api/users/' + userid, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })

        if (response.ok) {
            await getAllUsers();
            alert('User has been deleted')
        } else {
            alert('An error occurred while trying to delete')
        }
    })

    //Добавление пользователя
    $(document).on('click', '.submitAddUser', async function () {
        const addForm = $('#addForm')
        const formData = new FormData(addForm[0])

        let user = {
            id: 0,
            name: formData.get('name'),
            lastName: formData.get('lastName'),
            age: formData.get('age'),
            username: formData.get('username'),
            password: formData.get('password'),
            roles: $('#addRolesSelect').val().map(role => ({name: role}))
        };

        const response = await fetch('../api/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })

        if (response.ok) {
            await getAllUsers();
            alert('User has been added')
        } else {
            alert('An error occurred while trying to add')
        }

        addForm.find('input[type="text"], input[type="password"]').val('')
    })

    getUserInfo()
    getAllUsers()
    getAllRoles()


})