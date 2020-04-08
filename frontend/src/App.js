import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";
import WelcomePage from "./WelcomePage";
import Register from "./Register";
import Login from "./Login";
import axios from 'axios';

// axios.defaults.withCredentials = true;
// axios.defaults.headers.post['Content-Type'] = 'application/json';
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
        </Switch>
      </div>
    </Router>
  );
}
