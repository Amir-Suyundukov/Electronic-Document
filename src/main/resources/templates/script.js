document.addEventListener('DOMContentLoaded', () => {
    const content = document.getElementById('content');

    document.getElementById('employees-link').addEventListener('click', () => loadEmployees());
    document.getElementById('organizations-link').addEventListener('click', () => loadOrganizations());
    document.getElementById('subdivisions-link').addEventListener('click', () => loadSubdivisions());
    document.getElementById('tasks-link').addEventListener('click', () => loadTasks());

    function loadEmployees() {
        fetch('/api/employees')
            .then(response => response.json())
            .then(employees => {
                content.innerHTML = `
                    <h2>Employees</h2>
                    <button onclick="addEmployee()">Add Employee</button>
                    <ul>${employees.map(emp => `<li>${emp.name} - ${emp.position}</li>`).join('')}</ul>
                `;
            });
    }

    function loadOrganizations() {
        fetch('/api/organizations')
            .then(response => response.json())
            .then(organizations => {
                content.innerHTML = `
                    <h2>Organizations</h2>
                    <button onclick="addOrganization()">Add Organization</button>
                    <ul>${organizations.map(org => `<li>${org.name}</li>`).join('')}</ul>
                `;
            });
    }

    function loadSubdivisions() {
        fetch('/api/subdivisions')
            .then(response => response.json())
            .then(subdivisions => {
                content.innerHTML = `
                    <h2>Subdivisions</h2>
                    <button onclick="addSubdivision()">Add Subdivision</button>
                    <ul>${subdivisions.map(sub => `<li>${sub.name}</li>`).join('')}</ul>
                `;
            });
    }

    function loadTasks() {
        fetch('/api/tasks')
            .then(response => response.json())
            .then(tasks => {
                content.innerHTML = `
                    <h2>Tasks</h2>
                    <button onclick="addTask()">Add Task</button>
                    <ul>${tasks.map(task => `<li>${task.name} - ${task.status}</li>`).join('')}</ul>
                `;
            });
    }

    // Function templates for adding new entries (to be implemented)
    window.addEmployee = function() {
        content.innerHTML = '<h2>Add Employee</h2><form id="employee-form"><input type="text" placeholder="Name" id="employee-name"><input type="text" placeholder="Position" id="employee-position"><button type="submit">Save</button></form>';
        document.getElementById('employee-form').addEventListener('submit', (e) => {
            e.preventDefault();
            const name = document.getElementById('employee-name').value;
            const position = document.getElementById('employee-position').value;
            fetch('/api/employees', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({name, position})
            }).then(() => loadEmployees());
        });
    }

    window.addOrganization = function() {
        content.innerHTML = '<h2>Add Organization</h2><form id="organization-form"><input type="text" placeholder="Name" id="organization-name"><button type="submit">Save</button></form>';
        document.getElementById('organization-form').addEventListener('submit', (e) => {
            e.preventDefault();
            const name = document.getElementById('organization-name').value;
            fetch('/api/organizations', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({name})
            }).then(() => loadOrganizations());
        });
    }

    window.addSubdivision = function() {
        content.innerHTML = '<h2>Add Subdivision</h2><form id="subdivision-form"><input type="text" placeholder="Name" id="subdivision-name"><button type="submit">Save</button></form>';
        document.getElementById('subdivision-form').addEventListener('submit', (e) => {
            e.preventDefault();
            const name = document.getElementById('subdivision-name').value;
            fetch('/api/subdivisions', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({name})
            }).then(() => loadSubdivisions());
        });
    }

    window.addTask = function() {
        content.innerHTML = '<h2>Add Task</h2><form id="task-form"><input type="text" placeholder="Name" id="task-name"><select id="task-status"><option value="NEW">New</option><option value="IN_PROGRESS">In Progress</option><option value="COMPLETED">Completed</option></select><button type="submit">Save</button></form>';
        document.getElementById('task-form').addEventListener('submit', (e) => {
            e.preventDefault();
            const name = document.getElementById('task-name').value;
            const status = document.getElementById('task-status').value;
            fetch('/api/tasks', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({name, status})
            }).then(() => loadTasks());
        });
    }
});
