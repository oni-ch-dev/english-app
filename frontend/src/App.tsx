import { useState } from 'react'
import { ReviewSession } from './ReviewSession'
import { AddExpressionForm } from './AddExpressionForm'
import './App.css'


function App() {
  const [view, setView] = useState<'review' | 'add'>('review')

  return (
    <main className="app">
      <nav className="nav">
        <button className={view === 'review' ? 'active' : ''} onClick={() => setView('review')}>Review</button>
        <button className={view === 'add' ? 'active' : ''} onClick={() => setView('add')}>Add</button>
      </nav>
      {view === 'review' ? (
        <>
          <h1>Review</h1>
          <ReviewSession />
        </>
      ) : (
        <>
          <h1>Add Expression</h1>
          <AddExpressionForm />
        </>
      )}
    </main>
  )
}

export default App
