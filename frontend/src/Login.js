import React from 'react';
import lock from "./img/lock.svg";
import axios from "axios";
import './style.css';
import Logo from "./img/logo_transparent.png";

export default class Login extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: ""
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit(event) {
    const {email, password} = this.state;

    let result = axios
      .post(
        "http://localhost:8080/j_spring_security_check",
        {
            username: email,
            password: password
        }
      );

    result.then(response => {
      alert(response.data);
      this.setState({redirectToWelcome: true});
    });

    result.catch(error => {
      console.log("login error", error);
      alert("Błąd w trakcie logowania")
    });
    event.preventDefault();
  }

  render() {
    return (
      <div>
        <nav className="navbar navbar-expand-md navbar-light sticky-top">
          <div className="container-fluid">
            <a className="navbar-brand" href="/">
              <img alt="Let's work" src={Logo} className="img-fluid"/>
            </a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
              <span
                className="navbar-toggler-icon">
              </span>
            </button>
            <div className="collapse navbar-collapse" id="navbarResposive">
              <ul className="navbar-nav ml-auto">
                <div className="btn-group">
                  <a className="btn bg-primary" href="/register">Załóż konto</a>
                  <a className="btn btn-primary" href="/login">Zaloguj się</a>
                </div>
              </ul>
            </div>
          </div>
        </nav>
        <div className="base-container text-center form-group">
          <div className="container register-form">
            <div className="form">
              <div className="image">
                <img src={lock} alt="bg img"/>
              </div>
              <div className="form-content">
                <div className="row">
                  <div className="col-md-6">
                    <div className="form-group">
                      <label id="email"/>
                      <input
                        value={this.state.email}
                        name="email"
                        type="email"
                        className="form-control"
                        placeholder="Twój e-mail *"
                        onChange={this.handleChange}
                      />
                    </div>
                    <label id="password"/>
                    <input
                      value={this.state.password}
                      name="password"
                      type="password"
                      className="form-control"
                      placeholder="Twoje hasło *"
                      onChange={this.handleChange}
                    />
                  </div>
                  <div className="col-md-6">
                    <div className="form-group">
                      <button type="button" className="btnSubmit" onClick={this.handleSubmit}>Zaloguj się!</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <footer>
          <div className="container-fluid padding">
            <div className="row text-center">
              <div className="col-md-1">
                <img src={Logo} alt="logo"/>
                <hr className="dark"/>
                <p>
                  777 777 777
                </p>

              </div>
            </div>
            <div className="text-center">
              <div className="dark"/>
              <p>©lets-work-pl.pl</p>
            </div>
          </div>
        </footer>
      </div>
    );
  }
}