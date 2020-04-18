let isAdminMode = false;

// const tableTmpl = `<tr>
//                     <td><%= number %></td>
//                     <td><%= name %></td>
//                     <td><%= cost %></td>
//                     <td><%= count %></td>
//                     <td onClick="removeProduct(<%= id %>)">Remove</td>
//                 </tr>`;


const getAllRequest = (callback) => {
    $.ajax({
        method: "GET",
        url: "/rest/getAll",
        success: function (data) {
            drawTable(data);
            if (callback) callback();
        },
        error: function(){
            showError();
        }
    });
};

const addProduct = () => {
    $('#addNewProductContainer').hide();
    const name = $('#nameInput').val();
    const cost = $('#costInput').val();
    const count = $('#countInput').val();
    $.ajax({
        method: "POST",
        url: `/rest/add`,
        data: {name, cost, count},
        success: function () {
            getAllRequest();
        },
        error: function(){
            showError();
        }
    });
};

const removeProduct = (id) => {
    $.ajax({
        method: "DELETE",
        url: `/rest/delete/${id}`,
        success: function () {
            getAllRequest();
        },
        error: function(){
            showError();
        }
    });
};

const showGoods = () => {
    const mode = $('#modeSelector input:checked').val();
    isAdminMode = mode === 'admin';
    getAllRequest(() => {
        $('#welcome').hide();
        $('#tableContainer').show();
        if (isAdminMode) {
            $('#addProductContainer').show();
        }
    });
}

const showAddProduct = () => {
    $('#addNewProductContainer').show();
}

let errorInterval;

const showError = () => {
    clearInterval(errorInterval);
    $('#errorMassage').show();
    errorInterval = setInterval(() => {
        $('#errorMassage').hide();
    }, 3000)
}

const drawTable = (elements) => {
    const trHtml = elements.map((el, i) => {
        const {id, name, cost, count} = el;
        const rowTemplate = $('#rowTemplate').html();
        return _.template(rowTemplate)({id, number: id, name, cost, count, isAdminMode});
    })
    const tableTemplate = $('#tableTemplate').html();
    const tableHtml = _.template(tableTemplate)({isAdminMode});
    const table = $(tableHtml);
    table.find('tbody').append(trHtml.join(''));
    $('#tableContainer').html(table);
}

const editProduct = (rowId, id, number, name, cost, count) => {
    const rowTemplate = $('#editableRowTemplate').html();
    const row = _.template(rowTemplate)({id, number, name, cost, count, isAdminMode});
    $(`#${rowId}`).replaceWith(row);
}

const confirmEditing = (rowId, id) => {
    const inputs = $(`#${rowId}`).find('input');
    const name = inputs[0].value;
    const cost = inputs[1].value;
    const count = inputs[2].value;
    $.ajax({
        method: "PUT",
        url: `/rest/update`,
        data: {id, name, cost, count},
        success: function () {
            getAllRequest();
        },
        error: function(){
            showError();
        }
    });
}

