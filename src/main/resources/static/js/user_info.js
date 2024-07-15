jQuery(async function () {
    async function getUserInfo() {
        let response = await fetch('../api/users/userinfo');
        let userInfoJSON = await response.json();
        let roleList = ''
        userInfoJSON.roles.forEach((role) => {
            roleList += role.name.replaceAll('ROLE_', '') + ' ';
        })
        $('.userinfo').text(userInfoJSON.username.toUpperCase() + ' with roles: ' + roleList);

        $('.userInfo').append(
            '<tr>' +
            '<th scope="row">' + userInfoJSON.id + '</th>' +
            '<td>' + userInfoJSON.name + '</td>' +
            '<td>' + userInfoJSON.lastName + '</td>' +
            '<td>' + userInfoJSON.age + '</td>' +
            '<td>' + userInfoJSON.username + '</td>' +
            '<td>' + roleList + '</td>'
        )
    }
    getUserInfo()
})