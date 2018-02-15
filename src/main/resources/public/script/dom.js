$(document).ready(function () {

    const dom = {

        init: function () {
            eventApplier.addEventToItemToggle();
            eventApplier.addEventToServiceToggle();
            eventApplier.addEventToFilterButtons();
            eventApplier.addEventToStatusToggle();
            eventApplier.addEventToItemToggleInModal();
            eventApplier.addEventToServiceToggleInModal();
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

        toggleStatusCategories: function () {
            $("#statusButtons").slideToggle();
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
        },

        toggleItemCategoriesInModal: function () {
            $("#categorySelect").empty();
            console.log("sljekglaswkhgkjlasdgkljadrhglkadjrhgkljadhg");
            ajax.getItemCategories();
        },

        toggleServiceCategoriesInModal: function () {
            $("#categorySelect").empty();
            console.log("sljekglaswkhgkjlasdgkljadrhglkadjrhgkljadhg2");
            ajax.getServiceCategories();
        },

        buildCategoryToggle: function (response) {
            $.each(response, function (i, category) {
                console.log(category.name)
                $("#categorySelect").append(`
                    <option value=${category.id}>${category.name}</option>`)
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
        },

        addEventToStatusToggle: function () {
            $("#toggleStatuses").click(events.toggleStatusCategories);
        },

        addEventToItemToggleInModal: function () {
            $("#itemSelected").click(events.toggleItemCategoriesInModal)
        },

        addEventToServiceToggleInModal: function () {
            $("#serviceSelected").click(events.toggleServiceCategoriesInModal)
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
        },

        getItemCategories: function () {
            $.ajax({
                type: "GET",
                url: "/get-item-categories",
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    console.log("valamit amit felismerünk");
                    events.buildCategoryToggle(response);
                }
            });


        },

        getServiceCategories: function () {
            $.ajax({
                type: "GET",
                url: "/get-service-categories",
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    events.buildCategoryToggle(response);
                }
            });
        }
    };


    dom.init();

});
