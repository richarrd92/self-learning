import React from "react";
import { Outlet } from "react-router-dom"; // import Outlet from react-router-dom
import Header from "./components/Header/Header";
import Footer from "./components/Footer/Footer";

function Layout() {
  return (
    // there should always be a header and footer
    <>
      <Header />
      <Outlet /> {/* this is where the children will be rendered} */}
      {/* <Footer /> */}
    </>
  );
}

export default Layout;
