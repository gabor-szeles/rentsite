$(document).ready(function () {

    const dom = {

        init: function () {
            eventApplier.addEventToItemToggle();
            eventApplier.addEventToServiceToggle();
            eventApplier.addEventToFilterButtons();
        }

    };

    const events = {

        toggleItemCategories: function () {
            $("#itemButtons").slideToggle();
        },

        toggleServiceCategories: function () {
            $("#serviceButtons").slideToggle();
        },

        filter: function (event) {
            let id = $(event.target).attr("id");
            ajax.refreshIndex(id);
        },

        buildFilteredTable: function (response) {
            $("#datatable").empty();
            $('#datatable').append(`<tr>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th>User</th>
                                        <th>Price</th>
                                        <th>Status</th>
                                    </tr>`);
            $.each(response, function (i, rentable) {
                $("#datatable").append(
                    `<tr>
                        <td class="lead">${rentable.name}</td>
                        <td class="lead">${rentable.description}</td>
                        <td class="lead">${rentable.User}</td>
                        <td class="lead">${rentable.price}</td>
                        <td class="lead">${rentable.status}</td>
                    </tr>`)
            })

        }


    };

    const eventApplier = {

        addEventToServiceToggle: function () {
            $("#toggleServices").click(events.toggleServiceCategories);
        },
        addEventToItemToggle: function () {
            $("#toggleItems").click(events.toggleItemCategories);
        },

        addEventToFilterButtons: function () {
            $(".btn-secondary").click(events.filter);
        }

    };

    const ajax = {

        refreshIndex: function (id) {
            $.ajax({
                type: "POST",
                url: "/filter",
                dataType: "json",
                data: JSON.stringify(id),
                contentType: "application/json",
                success: function (response) {
                    events.buildFilteredTable(response);
                }
            });
        }
    };


    dom.init();

});
