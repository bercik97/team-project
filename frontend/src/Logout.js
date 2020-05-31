import React, {Component} from "react";
import axios from "axios";
import {Redirect} from "react-router-dom";

export default class Logout extends Component {

  constructor(props) {
    super(props);
    this.state = {
      redirectToMainPage: false,
    };
  }

  componentDidMount() {
    axios.post('http://localhost:8080/j_spring_security_logout').then(response => {
      this.setState({redirectToMainPage: true});
    });
  }

  render() {
    if (this.state.redirectToMainPage) {
      return <Redirect to="/"/>;
    }
    return ""
  }
}