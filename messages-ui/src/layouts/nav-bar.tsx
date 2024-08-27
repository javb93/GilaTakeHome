//Horizontal nav bar with only 2 links, one to messages and one to log history
import { FC } from "react";
import {
  StyledLink,
  StyledNavBar,
  StyledNavElement,
} from "../components/nav-bar-styled";

const NavBar: FC = () => {
  return (
    <StyledNavBar>
      <ul>
        <StyledNavElement>
          <StyledLink to="/messages">Messages</StyledLink>
        </StyledNavElement>
        <StyledNavElement>
          <StyledLink to="/log">Log</StyledLink>
        </StyledNavElement>
      </ul>
    </StyledNavBar>
  );
};

export default NavBar;
