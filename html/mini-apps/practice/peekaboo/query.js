$("#button1").click(function () {
  $("#notes1").show();
  $("#button1").hide();
  $("#button2").show();
});

$("#button2").click(function () {
  $("#notes1").hide();
  $("#button1").show();
  $("#button2").hide();
});
