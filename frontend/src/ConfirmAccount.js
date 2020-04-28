import React from 'react';
import axios from "axios";
import {Redirect} from "react-router-dom";
const qs = require('querystring')


export default class ConfirmAccount extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      redirectToLogin: false,
      isError: false,
    };
  }

  componentDidMount() {
    const token = window.location.href.split("=")[1];
    const config = {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }

    let result = axios
      .post(
        "http://localhost:8080/api/users/confirm-account",
        qs.stringify({
          token: token
        }),
        config
      );

    result.then(response => {
      this.setState({redirectToLogin: true});
    });

    result.catch(error => {
      this.setState({isError: true});
    });

  }

  render() {
    if (this.state.redirectToLogin) {
      return <Redirect to="/login" />;
    }

    if (this.state.isError) {
      return (<h1>Wystapil blad w trakcie potwierdzania tokenu</h1>)
    }

    return (<div> </div>);
  }
}