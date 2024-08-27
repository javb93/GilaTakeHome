import React from "react";
import styled from "styled-components";

export const StyledForm = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 20px;
  input {
    margin-bottom: 10px;
    width: 30%;
  }
  select {
    margin-bottom: 10px;
    width: 30%;
  }
  button {
    margin-top: 10px;
  }
`;
