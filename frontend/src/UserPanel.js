import React from 'react';
import Logo from "./img/logo_transparent.png";
import axios from 'axios';
import {Link} from "react-router-dom";
import Moment from 'react-moment';
import 'moment-timezone';

const initialState = {
  categories: [],
  categoryName: "",
  title: "",
  content: "",
  notices: "",
  redirectToOtherUserProfile: false,
  message: "",
  titleError: "",
  contentError: "",
  name: "",
  jobApplicationsMap: {},
};


export default class UserPanel extends React.Component {
  constructor(props) {
    super(props);
    this.state = initialState;
    this.fetchAdvertisements = this.fetchAdvertisements.bind(this);
    this.handleNewTitleChange = this.handleNewTitleChange.bind(this);
    this.handleNewContentChange = this.handleNewContentChange.bind(this);
  }

  handleNewTitleChange(event) {
    this.setState({
      "newTitle": event.target.value
    });
  }

  handleNewContentChange(event) {
    this.setState({
      "newContent": event.target.value
    });
  }

  deleteAdvertisement(notice) {
    axios.delete("http://localhost:8080/api/notices/delete/" + notice.id)
      .then(response => {
        this.fetchAdvertisements()
      })
  }

  getJobApplications(notice) {
    axios.get('http://localhost:8080/api/job-applications/' + notice.id).then(response => {
      const jobApplications = this.state.jobApplicationsMap;
      jobApplications[notice.id] = response.data;
      this.setState({jobApplicationsMap: jobApplications})
    })
  }

  componentDidMount() {
    axios.get('http://localhost:8080/api/auth').then(response => {
      console.log(response.data.name);
      this.setState({name: response.data.name});
    })

    axios.get('http://localhost:8080/api/categories').then(response => {

      this.setState({categories: response.data});
    })
    this.fetchAdvertisements();
  }

  updateNoticeValidate() {
    let newTitleError = "";
    let newContentError = "";
    if (!this.state.newContent) {
      newContentError = "Zawartość ogłoszenia nie może być pusta.";
    }

    if (!this.state.newTitle) {
      newTitleError = "Tytuł ogłoszenia nie może być pusty.";
    }

    if (newTitleError || newContentError) {
      this.setState({newTitleError, newContentError});
      return false;
    }
    return true;
  }

  updateAdvertisement(notice) {
    const isValid = this.updateNoticeValidate();
    if (isValid) {
      console.log(this.state);
      this.setState({initialState});
    }
    const data = {
      "newContent": this.state.newContent,
      "newTitle": this.state.newTitle
    }
    axios.put("http://localhost:8080/api/notices/update/" + notice.id, data)
      .then(response => {
        window.location.reload(false);
      })

  }

  fetchAdvertisements() {
    const categoryUrl = this.state.categoryName ? this.state.categoryName + "/" : "";

    axios.get('http://localhost:8080/api/notices/find/' + categoryUrl).then(response => {
      this.setState({notices: response.data});
      console.log("this.state.categories")
    })
  }

  renderAllNotices() {
    const renderedNotices = [];
    for (let notice of this.state.notices) {
      if (notice.authorEmail === this.state.name) {
        renderedNotices.push(this.offerItem(notice));
      }
    }
    return (
      <div>
        {renderedNotices}
      </div>
    )
  }

  offerItem(notice) {
    const modalId = "exampleModal" + notice.id;
    const editModalId = "edit" + notice.id;
    const editDataTarget = "#" + editModalId;
    const noticeId = "noticeId" + notice.id;

    const noticeJobApplications = this.state.jobApplicationsMap[notice.id];
    const parsedJobApplications = [];
    if (noticeJobApplications) {
      for (let jobApplication of noticeJobApplications) {
        parsedJobApplications.push(this.renderJobApplication(jobApplication))
      }
    }

    return (
      <div>
        <li className="timeline-item bg-white rounded col-md-12 p-4 shadow">
          <div className="timeline-arrow"/>
          <div className="row">
            <div className="col-md-11">
              <h2 className="h5 mb-0">
                {notice.title}
              </h2>
            </div>
            <div className="dropleft text-right pull-right">
              <button
                className="btn btn-secondary dropdown-toggle"
                type="button"
                id="dropdownMenuButton"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false">
              </button>
              <div className="dropdown-menu" aria-labelledby="dropdownMenu2">
                <button
                  className="dropdown-item"
                  type="button"
                  data-toggle="modal"
                  onClick={() => {
                    this.setState({newTitle: notice.title, newContent: notice.content})
                  }}
                  data-target={editDataTarget}>Edytuj
                </button>
                <button
                  className="dropdown-item"
                  type="button"
                  onClick={() => this.deleteAdvertisement(notice)}>
                  Usuń
                </button>
              </div>
            </div>
          </div>
          <span className="badge badge-info">
          {notice.categoryName}
        </span>
          <span className="small text-gray">
          <i className="fa fa-clock-o mr-1"/>
          <Moment format="YYYY/MM/DD">
            {notice.date}
          </Moment>
        </span>
          <p>
            Dodano przez: <span className="badge badge-pill badge-light">{notice.authorEmail}</span>
          </p>
          <p className="text-small mt-2 font-weight-light">
            {notice.content}
          </p>
          <p>
            <button
              className="btn btn-primary"
              data-toggle="collapse"
              href={"#" + noticeId}
              role="button"
              aria-expanded="false"
              onClick={() => {
                this.getJobApplications(notice)
              }}
            >
              Nadesłane zgłoszenia
            </button>
          </p>
          <div className="row">
            <div className="col">
              <div className="collapse multi-collapse" id={noticeId}>
                {parsedJobApplications.length !== 0 ? parsedJobApplications : <p>Brak nadesłanych ogłoszeń</p>}
              </div>
            </div>
          </div>

          <div className="modal fade" id={modalId} tabIndex="-1" role="dialog" aria-labelledby="exampleModalLabel"
               aria-hidden="true">
            <div className="modal-dialog" role="document">
              <div className="modal-content">
                <div className="modal-header">
                  <h5 className="modal-title" id="exampleModalLabel">Stwórz swoje zgłoszenie </h5>
                  <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div className="modal-body">
                  <form>
                    <div className="form-group">
                      <label htmlFor="recipient-name" className="col-form-label">Pracodawca:</label>
                      <label type="text" className="form-control" id="recipient-name">{notice.authorEmail}</label>
                    </div>
                    <div className="form-group">
                      <label htmlFor="message-text" className="col-form-label">Treść:</label>
                      <textarea className="form-control" id="message-text"/>
                    </div>
                  </form>
                </div>
                <div className="modal-footer">
                  <button type="button" className="btn btn-secondary" data-dismiss="modal">Zamknij</button>
                  <button type="button" className="btn btn-primary">Zapisz i wyślij!</button>
                </div>
              </div>
            </div>
          </div>
        </li>

        <div className="modal fade bd-example-modal-lg"
             id={editModalId}
             tabIndex="-1"
             role="dialog"
             aria-labelledby="exampleModalLabel"
             aria-hidden="true">
          <div className="modal-dialog modal-lg" role="document">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title" id="exampleModalLabel">Edytuj swoje ogłoszenie</h5>
                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div className="modal-body">
                <div className="custom-control custom-checkbox">
                  <div className="form-group">
                    <label htmlFor="title" className="col-form-label">Tytuł ogłoszenia:</label>
                    <input
                      type="text"
                      className="form-control"
                      id="recipient-name"
                      value={this.state.newTitle}
                      onChange={this.handleNewTitleChange}
                    />

                    <div style={{color: 'red'}}>
                      {this.state.newTitleError}
                    </div>
                  </div>
                  <div className="form-group">
                    <label htmlFor="content" className="col-form-label">Treść ogłoszenia:</label>
                    <textarea
                      className="form-control"
                      id="content"
                      value={this.state.newContent}
                      onChange={this.handleNewContentChange}
                    />
                    <div style={{color: 'red'}}>
                      {this.state.newContentError}
                    </div>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button
                  type="button"
                  className="btn btn-secondary"
                  data-dismiss="modal">
                  Zamknij
                </button>
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={() => this.updateAdvertisement(notice)}>
                  Zapisz i dodaj
                </button>
              </div>
            </div>
          </div>
        </div>

      </div>
    )
  }

  renderJobApplication(jobApplication) {
    return (
      <div className="card card-body">
        <p>{jobApplication.email}</p>
        <p>{jobApplication.message}</p>
      </div>
    );
  }

  render() {
    return (
      <div className="layout">
        <nav className="navbar navbar-expand-md navbar-light sticky-top">
          <div className="container-fluid">
            <a className="navbar-brand" href="/dashboard">
              <img alt="Let's work" src={Logo} className="img-fluid"/>
            </a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
            <span className="navbar-toggler-icon">
            </span>
            </button>
            <div className="collapse navbar-collapse" id="navbarResposive">
              <ul className="navbar-nav ml-auto">
                <div className="btn-group">
                  <Link to="/logout" className="btn bg-primary">Wyloguj się</Link>
                </div>
              </ul>
            </div>
          </div>
        </nav>

        <div className="container">
          <div className="row profile">
            <div className="col-md-3">
              <div className="profile-sidebar">
                <div className="profile-userpic">
                  <img
                    className="img-responsive" alt=""/>
                </div>
                <div className="profile-usertitle">
                  <div className="profile-usertitle-name">
                    {this.state.name}
                  </div>
                </div>
                <div className="profile-usermenu">
                  <ul className="nav">
                    <li className="active">
                      <a href="#">
                        <i className="glyphicon glyphicon-home"/>
                        Przegląd </a>
                    </li>
                    <li>
                      <a href="#">
                        <i className="glyphicon glyphicon-user"/>
                        Ustawienia konta </a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <div className="col-md-9">
              <div className="profile-content">
                <ul className="timeline">
                  {this.renderAllNotices()}
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}