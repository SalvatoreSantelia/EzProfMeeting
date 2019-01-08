$(document).ready(
    function () {

      $("[type='number']").keypress(function (evt) {
          evt.preventDefault();
      });
  }
);