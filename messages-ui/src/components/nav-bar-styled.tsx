import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";

export const StyledNavBar = styled.nav`
  background-color: #d3d3d3;
  color: white;
  padding-bottom: 10px;
  font-size: 1.5em;
  display: flex;
  justify-content: center;
  ul {
    list-style-type: none;
    width: 100%;
    display: flex;
    justify-content: space-around;
  }
`;
export const StyledNavElement = styled.li`
  display: inline;
  padding: 10px;
`;
export const StyledLink = styled(Link)`
  text-decoration: none;
  border: 2px solid black;
  border-radius: 5px;
  box-shadow: 2px 2px 2px black;
  padding: 5px;
  color: black;
  &:hover {
    color: blue;
    border: 2px solid blue;
    box-shadow: 2px 2px 2px blue;
  }
  &:active {
    color: green;
    border: 2px solid green;
    box-shadow: 1px 1px 1px green;
  }
`;
