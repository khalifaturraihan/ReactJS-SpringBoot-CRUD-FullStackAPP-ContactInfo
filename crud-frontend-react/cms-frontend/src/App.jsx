import './App.css'
import ContactComponent from './components/ContactComponent'
import FooterComponent from './components/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import ListContactComponent from './components/ListContactComponent'
import {BrowserRouter, Routes, Route} from 'react-router-dom'

function App() {

  return (
    <>
      <BrowserRouter>
        <HeaderComponent/>
          <Routes>
            {/* // http://localhost:3000 */}
              <Route path='/' element = { <ListContactComponent/> }></Route>
              {/* // http://localhost:3000/contact */}
              <Route path='/contact' element = { <ListContactComponent/> }></Route>
              {/* // http://localhost:3000/add-contact */}
              <Route path='/add-contact' element = { <ContactComponent/> }></Route>
              {/* // http://localhost:3000/edit-contact/1 */}
              <Route path='/edit-contact/:contactId' element = { <ContactComponent/> }></Route>
          </Routes>
        <FooterComponent/>
      </BrowserRouter>

    </>
  )
}

export default App
