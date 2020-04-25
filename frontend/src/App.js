import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";
import WelcomePage from "./WelcomePage";
import Register from "./Register";
import Login from "./Login";
import MainPageForLoggedIn from "./MainPageForLoggedIn";

export default function App() {
  return (
    <Router>
      <div>
        <Switch>
          <Route path="/register">
            <Register/>
          </Route>
          <Route exact path="/">
            <WelcomePage/>
          </Route>
          <Route path="/login">
            <Login/>
          </Route>
          <Route path="/mainpageforloggedin">
            <MainPageForLoggedIn/>
          </Route>
        </Switch>
      </div>
    </Router>
  );
}
