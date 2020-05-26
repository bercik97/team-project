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
import ConfirmAccount from "./ConfirmAccount";
import UserPanel from "./UserPanel";
import axios from 'axios';
import OtherUserProfile from "./OtherUserProfile";

axios.defaults.withCredentials = true;
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
          <Route path="/confirm-account">
            <ConfirmAccount/>
          </Route>
          <Route path="/userpanel">
            <UserPanel/>
          </Route>
          <Route path="/otheruserprofile">
            <OtherUserProfile/>
          </Route>
        </Switch>
      </div>
    </Router>
  );
}
